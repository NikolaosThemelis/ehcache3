/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehcache.core.config.store;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.EvictionVeto;
import org.ehcache.config.ResourcePools;
import org.ehcache.expiry.Expiry;
import org.ehcache.core.spi.cache.Store;
import org.ehcache.spi.serialization.Serializer;

/**
 *
 * @author Chris Dennis
 */
public class StoreConfigurationImpl<K, V> implements Store.Configuration<K, V> {

  private final Class<K> keyType;
  private final Class<V> valueType;
  private final EvictionVeto<? super K, ? super V> evictionVeto;
  private final ClassLoader classLoader;
  private final Expiry<? super K, ? super V> expiry;
  private final ResourcePools resourcePools;
  private final Serializer<K> keySerializer;
  private final Serializer<V> valueSerializer;
  private final int orderedEventParallelism;

  public StoreConfigurationImpl(CacheConfiguration<K, V> cacheConfig, int orderedEventParallelism,
                                Serializer<K> keySerializer, Serializer<V> valueSerializer) {
    this(cacheConfig.getKeyType(), cacheConfig.getValueType(), cacheConfig.getEvictionVeto(),
        cacheConfig.getClassLoader(), cacheConfig.getExpiry(), cacheConfig.getResourcePools(),
        orderedEventParallelism, keySerializer, valueSerializer);
  }

  public StoreConfigurationImpl(Class<K> keyType, Class<V> valueType,
                                EvictionVeto<? super K, ? super V> evictionVeto,
                                ClassLoader classLoader, Expiry<? super K, ? super V> expiry,
                                ResourcePools resourcePools, int orderedEventParallelism,
                                Serializer<K> keySerializer, Serializer<V> valueSerializer) {
    this.keyType = keyType;
    this.valueType = valueType;
    this.evictionVeto = evictionVeto;
    this.classLoader = classLoader;
    this.expiry = expiry;
    this.resourcePools = resourcePools;
    this.keySerializer = keySerializer;
    this.valueSerializer = valueSerializer;
    this.orderedEventParallelism = orderedEventParallelism;
  }

  @Override
  public Class<K> getKeyType() {
    return keyType;
  }

  @Override
  public Class<V> getValueType() {
    return valueType;
  }

  @Override
  public EvictionVeto<? super K, ? super V> getEvictionVeto() {
    return evictionVeto;
  }

  @Override
  public ClassLoader getClassLoader() {
    return this.classLoader;
  }

  @Override
  public Expiry<? super K, ? super V> getExpiry() {
    return expiry;
  }

  @Override
  public ResourcePools getResourcePools() {
    return resourcePools;
  }

  @Override
  public Serializer<K> getKeySerializer() {
    return keySerializer;
  }

  @Override
  public Serializer<V> getValueSerializer() {
    return valueSerializer;
  }

  @Override
  public int getOrderedEventParallelism() {
    return orderedEventParallelism;
  }
}